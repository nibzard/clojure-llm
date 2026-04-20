(defn has_close_elements
  "Check if in given list of numbers, are any two numbers closer to each other than
  given threshold."
  [numbers threshold]
  (let [sorted-numbers (sort numbers)]
    (boolean
      (some (fn [[a b]] (< (Math/abs (- a b)) threshold))
            (partition 2 1 sorted-numbers)))))