//
//  SoundManager.swift
//  BrewBrightness
//
//  Created by Sang hyun PARK on 4/10/26.
//

import AVFoundation

class SoundManager {
    
    static let shared = SoundManager()
    
    // 🔥 여러 player 유지 (핵심)
    private var players: [AVAudioPlayer] = []
    
    func play(name: String) {
        guard let url = Bundle.main.url(forResource: name, withExtension: "mp3") else {
            print("❌ sound file not found")
            return
        }
        
        do {
            let player = try AVAudioPlayer(contentsOf: url)
            player.prepareToPlay()
            player.play()
            
            // 🔥 retain
            players.append(player)
            
            // 🔥 자동 정리
            DispatchQueue.main.asyncAfter(deadline: .now() + player.duration) {
                self.players.removeAll { $0 === player }
            }
            
        } catch {
            print("❌ sound error")
        }
    }
}
