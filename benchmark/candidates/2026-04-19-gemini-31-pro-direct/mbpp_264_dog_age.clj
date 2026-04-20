(defn dog_age
  "Write a function to calculate a dog's age in dog's years."
  [h_age]
  (cond
    (or (nil? h_age) (< h_age 0)) nil
    (<= h_age 2) (* h_age 10.5)
    :else (+ 21 (* (- h_age 2) 4))))