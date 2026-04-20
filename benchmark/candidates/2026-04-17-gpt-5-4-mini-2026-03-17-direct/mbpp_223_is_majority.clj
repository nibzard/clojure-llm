(defn is_majority
  "	Write a function that takes in a sorted vector, its length (n), and an element and returns whether the element is the majority element in the given sorted vector. (The majority element is the element that occurs more than n/2 times.)"
  [arr n x]
  (let [i (loop [lo 0, hi n]
            (if (< lo hi)
              (let [mid (quot (+ lo hi) 2)]
                (if (< (compare (nth arr mid) x) 0)
                  (recur (inc mid) hi)
                  (recur lo mid)))
              lo))]
    (and (< i n)
         (= (nth arr i) x)
         (let [j (+ i (quot n 2))]
           (and (< j n)
                (= (nth arr j) x)))))