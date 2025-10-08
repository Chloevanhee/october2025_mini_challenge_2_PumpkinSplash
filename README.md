# Pumpkin Splash

A simple splash screen with a pumpkin and a defined sequence of animations.

## Context

This project was created for the october Mini Challenges in the Mobile Dev Campus by Philipp Lackner, using the provided Figma design and instructions.
If you are interested in joining the Campus, you can register here: https://pl-coding.com/campus/

## Scenario

When the app launches, a pumpkin appears, spins, slightly tilts behind the horizon line first to
the left, then to the right, performs a final tilt animation, and disappears

## Feature Goal

Implement a simple splash screen with a pumpkin and a defined sequence of animations.

## Requirements

### UI Components

- **Pumpkin Image**: Central element of the splash screen.

### Animation Sequence

1. **Appearance and Rotation**
    - Initial scale: `50%`
    - Grows to `100%` in `1.8s`
    - At the same time, performs `2` full counterclockwise rotations

2. **Tilt Left**
    - At the end, slightly tilts behind the horizon on the left

3. **Tilt to the Right and Back**
    - In `0.4s`, the pumpkin tilts to the right (behind the horizon) and immediately returns back to
      center

4. **Pause**
    - Remains still for `0.8s`

5. **Final Animation and Disappearance**
    - In `0.4s`, slightly scales up and tilts to the left
    - Then in `0.4s`, tilts to the right
    - Finally, in `0.8s`, shrinks to `0` and disappears

### Demo

<img src="docs/Screen_recording_20251008_222136.gif" alt="Demo of Pumpkin Splash" width="320" />
