(defn perimeter_pentagon
  "Write a function to find the perimeter of a regular pentagon from the length of its sides."
  [a]
  (when (some? a)
    (* 5 a)))