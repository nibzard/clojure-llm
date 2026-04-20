(defn nth-odd-prime
  "Return the nth odd prime number (1-indexed).

  Examples:
  (nth-odd-prime 1) => 3
  (nth-odd-prime 5) => 13

  Return nil for non-positive n."
  [n]
  (when (pos? n)
    (->> (iterate #(+ % 2) 3)
         (filter #(every? (fn [d] (not (zero? (mod % d))))
                          (range 3 (inc (Math/sqrt %)) 2)))
         (nth (dec n)))))