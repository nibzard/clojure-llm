(defn find_closest_elements
  "From a supplied list of numbers (of length at least two) select and return two that are the closest to each
  other and return them in order (smaller number, larger number)."
  [numbers]
  (let [sorted (sort numbers)
        pairs (partition 2 1 sorted)]
    (vec (apply min-key (fn [[a b]] (- b a)) pairs))))