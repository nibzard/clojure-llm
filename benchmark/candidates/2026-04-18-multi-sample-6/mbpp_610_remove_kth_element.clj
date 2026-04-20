(defn remove_kth_element
  "	Write a cljthon function which takes a list and returns a list with the same elements, but the k'th element removed."
  [list1 L]
  (let [idx (when (number? L) (dec (long L)))]
    (cond
      (or (nil? list1) (empty? list1)) list1
      (or (nil? idx) (neg? idx)) list1
      :else
      (concat (take idx list1) (drop (inc idx) list1)))))