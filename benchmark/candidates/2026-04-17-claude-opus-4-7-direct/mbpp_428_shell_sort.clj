(defn shell_sort
  "Write a function to sort the given vector by using shell sort."
  [my_list]
  (if (empty? my_list)
    []
    (let [n (count my_list)
          ;; Generate gaps using the Knuth sequence: 1, 4, 13, 40, 121, ...
          ;; Start with the largest gap less than n
          gaps (loop [g 1]
                 (if (> g n)
                   ()
                   (cons g (recur (* 3 g)))))]
      (loop [arr (vec my_list)
             gaps gaps]
        (if (empty? gaps)
          arr
          (let [gap (first gaps)]
            ;; Perform a gapped insertion sort for this gap size
            (let [sorted-arr
                  (loop [i gap
                         arr arr]
                    (if (>= i n)
                      arr
                      (let [temp (arr i)
                            ;; Shift elements that are gap positions apart
                            arr (loop [j i
                                       arr arr]
                                  (if (and (>= j gap)
                                           (> (arr (- j gap)) temp))
                                    (recur (- j gap)
                                           (assoc arr j (arr (- j gap))))
                                    arr))]
                        ;; Insert the original element at its correct position
                        (recur (inc i)
                               (assoc arr j temp)))))]
              (recur sorted-arr (rest gaps)))))))))