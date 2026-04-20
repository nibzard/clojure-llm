(defn even_position
  "Write a cljthon function to check whether every even index contains even numbers of a given list."
  [nums]
  (every? (fn [[idx num]]
            (or (odd? idx)
                (even? num)))
          (map vector (range) nums)))