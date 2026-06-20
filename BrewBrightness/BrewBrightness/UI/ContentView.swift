//
//  ContentView.swift
//  BrewBrightness
//
//  Created by Sang hyun PARK on 4/10/26.
//
import SwiftUI

struct ContentView: View {
    
    @StateObject private var manager = BrightnessManager()
    
    var body: some View {
        ZStack {
            
            // 🌑 어두워짐 효과
            Color.black
                .opacity(1.0 - manager.brightness)
                .ignoresSafeArea()
            
            BeerView(level: manager.brightness)
                .onTapGesture {
                    manager.drink()
                }
        }
        // 🔥 안정적인 자동 감소 (Timer)
        .onAppear {
            Timer.scheduledTimer(withTimeInterval: 0.05, repeats: true) { _ in
                manager.update()
            }
        }
    }
}
