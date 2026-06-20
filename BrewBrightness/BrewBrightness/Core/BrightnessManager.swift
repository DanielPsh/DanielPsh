//
//  BrightnessManager.swift
//  BrewBrightness
//
//  Created by Sang hyun PARK on 4/10/26.
//

import SwiftUI

class BrightnessManager: ObservableObject {
    
    @Published var brightness: Double = 1.0
    
    private let minBrightness: Double = 0.05
    
    func update() {
        brightness *= 0.995
        brightness = max(brightness, minBrightness)
    }
    
    func drink() {
        brightness = min(brightness + 0.2, 1.0)
    }
}
