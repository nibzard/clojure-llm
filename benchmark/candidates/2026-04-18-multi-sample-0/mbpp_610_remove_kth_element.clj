(defn remove_kth_element
  "	Write a cljthon function which takes a list and returns a list with the same elements, but the k'th element removed."
  [list1 L]
  (let [idx (dec L)]
    (cond
      (nil? list1) nil
      (or (nil? L) (neg? idx)) list1
      :else (concat (take idx list1) (drop (inc idx) list1)))))