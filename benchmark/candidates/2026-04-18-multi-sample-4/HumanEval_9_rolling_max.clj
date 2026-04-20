(defn rolling_max
  " From a given list of integers, generate a list of rolling maximum element found until given moment
  in the sequence.
  >>> (rolling_max [1 2 3 2 3 4 2])
  [1 2 3 3 3 4 4]"
  [numbers]
  (reduce (fn [acc n]
            (conj acc (if-let [m (peek acc)]
                        (max m n)
                        n)))
          []
          numbers))