(defn opposite-Signs
  "Check whether the given two integers have opposite sign or not."
  [x y]
  (and (not (zero? x))
       (not (zero? y))
       (not (== (Long/signum x) (Long/signum y)))))