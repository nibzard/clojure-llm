(defn intersection
  [interval1 interval2]
  (let [[s1 e1] interval1
        [s2 e2] interval2
        start (max s1 s2)
        end (min e1 e2)
        length (- end start)]
    (if (pos? length)
      (let [is-prime? (fn [n]
                        (or (= n 2)
                            (and (> n 2)
                                 (odd? n)
                                 (not-any? #(zero? (mod n %))
                                           (range 3 (inc (Math/sqrt n)) 2)))))]
        (if (is-prime? length) "YES" "NO"))
      "NO")))