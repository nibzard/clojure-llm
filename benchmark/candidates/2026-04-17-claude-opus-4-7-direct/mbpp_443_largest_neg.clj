(defn largest_neg
  "Write a cljthon function to find the largest negative number from the given list."
  [list1]
  (when (seq list1)
    (reduce (fn [max-val x]
              (if (and (neg? x) (or (nil? max-val) (> x max-val)))
                x
                max-val))
            nil
            list1)))