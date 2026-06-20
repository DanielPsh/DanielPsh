//
//  BubbleView.swift
//  BrewBrightness
//
//  Created by Sang hyun PARK on 4/10/26.
//

import SwiftUI

struct Bubble: Identifiable {
    let id = UUID()
    var x: CGFloat
    var offset: CGFloat
    var size: CGFloat
    var speed: Double
}

struct BubbleView: View {
    
    var level: Double
    @State private var bubbles: [Bubble] = []
    
    var body: some View {
        GeometryReader { geo in
            ZStack {
                
                ForEach(bubbles) { bubble in
                    Circle()
                        .fill(Color.white.opacity(0.4))
                        .frame(width: bubble.size, height: bubble.size)
                        .position(
                            x: bubble.x,
                            y: geo.size.height - bubble.offset
                        )
                        .animation(
                            .linear(duration: bubble.speed)
                            .repeatForever(autoreverses: false),
                            value: bubble.offset
                        )
                }
            }
            .onAppear {
                generateBubbles(size: geo.size)
            }
        }
    }
}

// MARK: - Safe Generator
extension BubbleView {
    
    func generateBubbles(size: CGSize) {
        
        bubbles.removeAll()
        
        for _ in 0..<25 {
            let bubble = Bubble(
                x: CGFloat.random(in: 0...size.width),
                offset: 0,
                size: CGFloat.random(in: 3...8),
                speed: Double.random(in: 2...5)
            )
            
            bubbles.append(bubble)
        }
        
        // 🔥 offset 변경 (한 번만)
        DispatchQueue.main.async {
            for i in bubbles.indices {
                bubbles[i].offset = size.height * level
            }
        }
    }
}
