//
//  GlassView.swift
//  BrewBrightness
//
//  Created by Sang hyun PARK on 4/10/26.
//

import SwiftUI

struct GlassView: View {
    
    var body: some View {
        ZStack {
            
            RoundedRectangle(cornerRadius: 24)
                .stroke(Color.white.opacity(0.6), lineWidth: 2)
            
            RoundedRectangle(cornerRadius: 24)
                .fill(
                    LinearGradient(
                        colors: [
                            Color.white.opacity(0.25),
                            Color.clear
                        ],
                        startPoint: .topLeading,
                        endPoint: .bottomTrailing
                    )
                )
            
            RoundedRectangle(cornerRadius: 24)
                .stroke(Color.white.opacity(0.3), lineWidth: 1)
                .blur(radius: 2)
        }
    }
}
