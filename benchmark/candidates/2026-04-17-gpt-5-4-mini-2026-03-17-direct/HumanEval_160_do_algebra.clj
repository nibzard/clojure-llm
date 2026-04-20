(defn do_algebra
  [operator operand]
  (let [ops {"+" +
             "-" -
             "*" *
             "//" (fn [a b] (quot a b))
             "**" (fn [a b] (long (Math/pow a b)))}]
    (reduce (fn [acc [op n]]
              ((ops op) acc n))
            (first operand)
            (map vector operator (rest operand)))))