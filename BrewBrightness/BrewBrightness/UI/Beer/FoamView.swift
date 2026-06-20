//
//  FoamView.swift
//  BrewBrightness
//
//  Created by Sang hyun PARK on 4/10/26.
//

import SwiftUI

struct FoamView: View {
    
    var level: Double
    
    var body: some View {
        GeometryReader { geo in
            
            let foamY = geo.size.height * (1 - level)
            
            RoundedRectangle(cornerRadius: 10)
                .fill(Color.white.opacity(0.9))
                .frame(height: 8)
                .position(x: geo.size.width / 2, y: foamY)
                .blur(radius: 2)
        }
    }
}
