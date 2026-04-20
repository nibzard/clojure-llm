(defn hsv-to-rgb
  "Convert an HSV color to RGB.

  Hue is expected in degrees in the range [0, 360), saturation and value in [0, 1].

  Examples:
  (hsv-to-rgb 0 1 1)     ;=> [255 0 0]
  (hsv-to-rgb 120 1 1)   ;=> [0 255 0]
  (hsv-to-rgb 240 1 1)   ;=> [0 0 255]
  (hsv-to-rgb 60 1 1)    ;=> [255 255 0]"
  [h s v])