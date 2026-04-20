(defn centered_hexagonal_number
  "	Write a function to find nth centered hexagonal number."
  [n]
  (when (some? n)
    (let [n' (bigint n)]
      (+ (* 3 n' (dec n')) 1))))