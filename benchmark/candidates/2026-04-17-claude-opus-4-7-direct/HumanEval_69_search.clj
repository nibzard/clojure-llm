(defn search
  "You are given a non-empty list of positive integers. Return the greatest integer that is greater than 
  zero, and has a frequency greater than or equal to the value of the integer itself. 
  The frequency of an integer is the number of times it appears in the list.
  If no such a value exist, return -1."
  [lst]
  (let [freqs (frequencies lst)
        candidates (filter (fn [[num count]] 
                             (and (pos? num) 
                                  (>= count num))) 
                           freqs)]
    (if (seq candidates)
      (apply max (keys candidates))
      -1)))