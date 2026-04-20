(defn rgb-to-hex
  "Convert an RGB color to a hex string.
   Accepts either three integers in the range 0-255 or a single map {:r r :g g :b b}.
   Returns a lowercase string like \"#ff00aa\".

   Examples:
   (rgb-to-hex 255 0 170) => \"#ff00aa\"
   (rgb-to-hex {:r 255 :g 0 :b 170}) => \"#ff00aa\""
  [r g b])