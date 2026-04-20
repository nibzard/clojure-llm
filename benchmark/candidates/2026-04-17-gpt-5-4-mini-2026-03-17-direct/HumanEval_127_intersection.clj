(defn intersection
  [interval1 interval2]
  (let [[a b] interval1
        [c d] interval2
        start (max a c)
        end (min b d)
        length (inc (- end start))
        prime? (fn [n]
                 (and (> n 1)
                      (not-any? #(zero? (mod n %))
                                (range 2 (inc (Math/sqrt n))))))]
    (if (pos? length)
      (if (prime? length) "YES" "NO")
      "NO")))