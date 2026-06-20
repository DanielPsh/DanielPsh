//
//  BeerView.swift
//  BrewBrightness
//
//  Created by Sang hyun PARK on 4/10/26.
//

import SwiftUI

struct BeerView: View {
    
    var level: Double
    
    var body: some View {
        ZStack {
            
            // 🍺 유리 테두리
            RoundedRectangle(cornerRadius: 20)
                .stroke(Color.white, lineWidth: 2)
                .frame(width: 140, height: 240)
            
            // 🍺 맥주
            VStack {
                Spacer()
                
                Rectangle()
                    .fill(
                        LinearGradient(
                            colors: [Color.yellow, Color.orange],
                            startPoint: .top,
                            endPoint: .bottom
                        )
                    )
                    .frame(height: 240 * level)
            }
            .frame(width: 130, height: 220)
            .clipShape(RoundedRectangle(cornerRadius: 18))
        }
        .shadow(color: .black.opacity(0.3), radius: 10, y: 5)
    }
}
