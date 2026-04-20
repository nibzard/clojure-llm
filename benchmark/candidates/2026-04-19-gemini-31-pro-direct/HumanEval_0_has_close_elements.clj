(defn has_close_elements
  "Check if in given list of numbers, are any two numbers closer to each other than
  given threshold."
  [numbers threshold]
  (boolean
   (some (fn [[a b]] (< (- b a) threshold))
         (partition 2 1 (sort numbers)))))