(defn is_majority
  "	Write a function that takes in a sorted vector, its length (n), and an element and returns whether the element is the majority element in the given sorted vector. (The majority element is the element that occurs more than n/2 times.)"
  [arr n x]
  (let [idx (or (first (keep-indexed (fn [i v] (when (= v x) i)) arr))
                -1)]
    (and (>= idx (quot n 2))
         (<= (+ idx 1) n))))