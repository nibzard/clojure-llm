(defn pancake_sort
  "Sorts a list of elements using the pancake sort algorithm."
  [nums]
  (let [cnt (count nums)]
    (loop [current-size cnt
           arr (vec nums)]
      (if (<= current-size 1)
        (seq arr)
        (let [;; Find the index of the maximum element in the current range [0, current-size)
              max-idx (loop [i 1
                             max-i 0]
                        (if (>= i current-size)
                          max-i
                          (if (> (arr i) (arr max-i))
                            (recur (inc i) i)
                            (recur (inc i) max-i))))
              ;; If max is already at the end, continue to next sub-array
              arr (if (= max-idx (dec current-size))
                    arr
                    ;; Otherwise, flip max to the front (if not already there)
                    (let [arr (if (zero? max-idx)
                                arr
                                (into (subvec arr 0 max-idx)
                                      (reverse (subvec arr max-idx current-size))))]
                      ;; Then flip the entire current range to move max to the end
                      (into [] (reverse (subvec arr 0 current-size)))))]
          (recur (dec current-size) arr))))))