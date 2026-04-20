(defn comb_sort
  "Write a function to sort a list of elements."
  [nums]
  (if (empty? nums)
    nums
    (let [n (count nums)]
      (loop [v (vec nums)
             gap n
             swapped? true]
        (let [next-gap (max 1 (int (/ gap 1.3)))
              [new-v new-swapped?]
              (loop [i 0
                     curr-v v
                     curr-swapped? false]
                (if (>= (+ i next-gap) n)
                  [curr-v curr-swapped?]
                  (let [a (curr-v i)
                        b (curr-v (+ i next-gap))]
                    (if (> a b)
                      (recur (inc i) (assoc curr-v i b (+ i next-gap) a) true)
                      (recur (inc i) curr-v curr-swapped?)))))]
          (if (and (= next-gap 1) (not new-swapped?))
            (seq new-v)
            (recur new-v next-gap new-swapped?)))))))