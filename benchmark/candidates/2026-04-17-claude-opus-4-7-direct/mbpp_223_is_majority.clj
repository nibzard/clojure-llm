(defn is_majority
  "Write a function that takes in a sorted vector, its length (n), and an element and returns whether the element is the majority element in the given sorted vector. (The majority element is the element that occurs more than n/2 times.)"
  [arr n x]
  (let [first-idx (clojure.core/binary-search arr x)
        last-idx  (clojure.core/binary-search arr x (fn [a b] (compare b a)))]
    (if (number? first-idx)
      (> (- (inc last-idx) first-idx) (/ n 2))
      false)))