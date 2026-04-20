(defn next_smallest
  "You are given a list of integers.
  Write a function next_smallest() that returns the 2nd smallest element of the list.
  Return nil if there is no such element.
  >>> (next_smallest [1 2 3 4 5])
  2
  >>> (next_smallest [5 1 4 3 2])
  2
  >>> (next_smallest [])
  nil
  >>> (next_smallest [1 1])
  nil"
  [lst]
  (when (seq lst)
    (let [[smallest second-smallest]
          (reduce (fn [[m1 m2] x]
                    (cond
                      (nil? m1) [x nil]
                      (< x m1) [x m1]
                      (= x m1) [m1 m2]
                      (or (nil? m2) (< x m2)) [m1 x]
                      :else [m1 m2]))
                  [nil nil]
                  lst)]
      second-smallest)))