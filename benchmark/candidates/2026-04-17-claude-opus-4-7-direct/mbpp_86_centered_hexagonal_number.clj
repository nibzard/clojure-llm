(defn centered_hexagonal_number
  "Write a function to find nth centered hexagonal number."
  [n]
  (inc (* 3 n (dec n))))