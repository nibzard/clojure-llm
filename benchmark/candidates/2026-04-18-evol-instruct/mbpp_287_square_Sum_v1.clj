(defn sum-even-squares-in-range
  "Return the sum of squares of all even numbers in the inclusive range [a, b].
  If a > b, return 0.
  
  Examples:
  (sum-even-squares-in-range 1 6) => 56   ; 2^2 + 4^2 + 6^2
  (sum-even-squares-in-range -3 3) => 4    ; only 2^2
  (sum-even-squares-in-range 5 2) => 0"
  [a b]
  (if (> a b)
    0
    (transduce (comp (filter even?) (map #(* % %)))
               +
               (range a (inc b)))))