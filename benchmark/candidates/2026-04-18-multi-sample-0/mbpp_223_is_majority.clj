(defn is_majority
  "	Write a function that takes in a sorted vector, its length (n), and an element and returns whether the element is the majority element in the given sorted vector. (The majority element is the element that occurs more than n/2 times.)"
  [arr n x]
  (let [n (or n 0)]
    (if (or (nil? arr) (zero? n))
      false
      (let [first-index
            (loop [lo 0 hi (dec n) ans -1]
              (if (> lo hi)
                ans
                (let [mid (quot (+ lo hi) 2)
                      v   (nth arr mid)]
                  (cond
                    (>= v x) (recur lo (dec mid) (if (= v x) mid ans))
                    :else    (recur (inc mid) hi ans)))))]
        (and (not= first-index -1)
             (< (+ first-index (quot n 2)) n)
             (= (nth arr (+ first-index (quot n 2))) x))))))