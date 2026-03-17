#!/bin/bash

player_name="$1"
shift

declare -A stats
declare -A counts

total_FGM=0
total_FGA=0
total_3PM=0
total_3PA=0
total_FTM=0
total_FTA=0

for stat in "$@"; do
    stats["$stat"]=0
    counts["$stat"]=0
done

for file in nba*.txt; do
    while read -r line; do

        [[ "$line" == *DNP* ]] && continue
        [[ "$line" == TOTALS* ]] && continue
        [[ -z "$line" ]] && continue

        if [[ "$line" =~ (^|[[:space:]])"$player_name"([[:space:]]|$) ]]; then

            read -ra fields <<< "$line"

            for i in "${!fields[@]}"; do
                if [[ "${fields[$i]}" == *:* ]]; then
                min_idx=$i
                break
                fi
            done

            fgm=${fields[$((min_idx+1))]}
            fga=${fields[$((min_idx+2))]}

            tpm=${fields[$((min_idx+4))]}
            tpa=${fields[$((min_idx+5))]}

            ftm=${fields[$((min_idx+7))]}
            fta=${fields[$((min_idx+8))]}

            reb=${fields[$((min_idx+12))]}
            ast=${fields[$((min_idx+13))]}
            stl=${fields[$((min_idx+14))]}
            pts=${fields[$((min_idx+18))]}


            total_FGM=$((total_FGM + fgm))
            total_FGA=$((total_FGA + fga))

            total_3PM=$((total_3PM + tpm))
            total_3PA=$((total_3PA + tpa))

            total_FTM=$((total_FTM + ftm))
            total_FTA=$((total_FTA + fta))

            for stat in "$@"; do
                case "$stat" in
                    PTS)
                        stats[PTS]=$((stats[PTS] + pts))
                        ((counts[PTS]++))
                        ;;
                    REB)
                        stats[REB]=$((stats[REB] + reb))
                        ((counts[REB]++))
                        ;;
                    AST)
                        stats[AST]=$((stats[AST] + ast))
                        ((counts[AST]++))
                        ;;
                    STL)
                        stats[STL]=$((stats[STL] + stl))
                        ((counts[STL]++))
                        ;;
                    FGM)
                        stats[FGM]=$((stats[FGM] + fgm))
                        ((counts[FGM]++))
                        ;;
                    3PM)
                        stats[3PM]=$((stats[3PM] + tpm))
                        ((counts[3PM]++))
                        ;;
                    FTM)
                        stats[FTM]=$((stats[FTM] + ftm))
                        ((counts[FTM]++))
                        ;;
                esac
            done

        fi
    done < "$file"
done

# Output results
for stat in "$@"; do
    case "$stat" in
        FG%)
            if [[ $total_FGA -gt 0 ]]; then
                val=$(awk "BEGIN { printf \"%.1f\", ($total_FGM/$total_FGA)*100 }")
                echo "FG% $val"
            else
                echo "FG% 0.0"
            fi
            ;;
        3P%)
            if [[ $total_3PA -gt 0 ]]; then
                val=$(awk "BEGIN { printf \"%.1f\", ($total_3PM/$total_3PA)*100 }")
                echo "3P% $val"
            else
                echo "3P% 0.0"
            fi
            ;;
        FT%)
            if [[ $total_FTA -gt 0 ]]; then
                val=$(awk "BEGIN { printf \"%.1f\", ($total_FTM/$total_FTA)*100 }")
                echo "FT% $val"
            else
                echo "FT% 0.0"
            fi
            ;;
        *)
            if [[ ${counts[$stat]} -gt 0 ]]; then
                avg=$(awk "BEGIN { printf \"%.1f\", ${stats[$stat]} / ${counts[$stat]} }")
                echo "$stat $avg"
            else
                echo "$stat 0.0"
            fi
            ;;
    esac
done 
