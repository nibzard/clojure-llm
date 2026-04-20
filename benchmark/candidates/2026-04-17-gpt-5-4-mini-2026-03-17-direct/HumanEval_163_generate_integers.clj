(defn generate_integers
  "Given two positive integers a and b, return the even digits between a
  and b, in ascending order."
  [a b]
  (let [start (min a b)
        end   (max a b)]
    (vec (filter even? (range start (inc end))))))