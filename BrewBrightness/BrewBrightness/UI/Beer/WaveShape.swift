//
//  WaveShape.swift
//  BrewBrightness
//
//  Created by Sang hyun PARK on 4/10/26.
//

import SwiftUI

struct WaveShape: Shape {
    
    var progress: Double
    var offset: Double
    
    func path(in rect: CGRect) -> Path {
        var path = Path()
        
        let height = rect.height * (1 - progress)
        let amplitude: CGFloat = 8
        
        path.move(to: CGPoint(x: 0, y: height))
        
        for x in stride(from: 0, through: rect.width, by: 1) {
            let y = height + amplitude * sin((x / rect.width) * .pi * 2 + offset)
            path.addLine(to: CGPoint(x: x, y: y))
        }
        
        path.addLine(to: CGPoint(x: rect.width, y: rect.height))
        path.addLine(to: CGPoint(x: 0, y: rect.height))
        path.closeSubpath()
        
        return path
    }
}
