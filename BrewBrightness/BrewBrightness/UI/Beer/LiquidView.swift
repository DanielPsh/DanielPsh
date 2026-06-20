//
//  LiquidView.swift
//  BrewBrightness
//
//  Created by Sang hyun PARK on 4/10/26.
//

import SwiftUI

struct LiquidView: View {
    
    var level: Double
    @State private var waveOffset: Double = 0
    
    var body: some View {
        ZStack {
            
            LinearGradient(
                colors: [Color.yellow, Color.orange],
                startPoint: .top,
                endPoint: .bottom
            )
            
            WaveShape(progress: level, offset: waveOffset)
                .fill(Color.yellow.opacity(0.9))
            
            BubbleView(level: level)
            FoamView(level: level)
        }
        .clipShape(RoundedRectangle(cornerRadius: 22))
        .onAppear { startWave() }
    }
    
    func startWave() {
        withAnimation(.linear(duration: 1.2).repeatForever(autoreverses: false)) {
            waveOffset = .pi * 2
        }
    }
}
