(defn longest-balanced-prefix
  "Return the length of the longest prefix of a sequence of 0s and 1s
  in which the number of 0s and 1s is equal.

  Treat nil as an empty sequence.

  Examples:
  (longest-balanced-prefix [0 1 1 0 1 0]) => 6
  (longest-balanced-prefix [0 0 1 1 1]) => 4
  (longest-balanced-prefix nil) => 0"
  [xs]
  (loop [s (seq xs)
         i 0
         zeros 0
         ones 0
         best 0]
    (if (nil? s)
      best
      (let [x (first s)
            zeros' (+ zeros (if (= x 0) 1 0))
            ones'  (+ ones  (if (= x 1) 1 0))
            best'  (if (= zeros' ones') i best)]
        (recur (next s) (inc i) zeros' ones' best')))))