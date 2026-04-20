(defn octagonal-seq
  "Return a lazy infinite sequence of octagonal numbers starting from the 1st.
  Works with any positive integer n and supports taking as many values as needed.

  Examples:
  (take 5 (octagonal-seq)) => (1 8 21 40 65)
  (nth (octagonal-seq) 3) => 40"
  []
  (map #(+ (* 3 % %) (- (* 2 %) 1)) (iterate inc 1)))