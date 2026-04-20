(defn rgb->hsl
  "Convert an RGB color to HSL.

  Accepts a single map {:r r :g g :b b} where each channel is an integer in [0,255].
  Returns a map {:h h :s s :l l} where:
  - h is hue in degrees [0,360)
  - s and l are percentages in [0,100]

  Examples:
  (rgb->hsl {:r 255 :g 0 :b 0})   ;=> {:h 0, :s 100, :l 50}
  (rgb->hsl {:r 0 :g 0 :b 0})     ;=> {:h 0, :s 0, :l 0}
  (rgb->hsl {:r 255 :g 255 :b 255}) ;=> {:h 0, :s 0, :l 100}"
  [color])