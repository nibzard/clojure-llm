(defn find_closest_elements
  " From a supplied list of numbers (of length at least two) select and return two that are the closest to each
  other and return them in order (smaller number, larger number).
  >>> (find_closest_elements [1.0 2.0 3.0 4.0 5.0 2.2])
  [2.0 2.2]
  >>> (find_closest_elements [1.0 2.0 3.0 4.0 5.0 2.0])
  [2.0 2.0]"
  [numbers]
  (let [sorted (sort numbers)]
    (->> (partition 2 1 sorted)
         (reduce (fn [[best-pair best-diff] [a b :as pair]]
                   (let [diff (- b a)]
                     (if (< diff best-diff)
                       [pair diff]
                       [best-pair best-diff])))
                 [nil Double/POSITIVE_INFINITY])
         first
         vec)))