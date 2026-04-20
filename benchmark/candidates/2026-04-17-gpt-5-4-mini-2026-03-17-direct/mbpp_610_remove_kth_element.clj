(defn remove_kth_element
  "	Write a cljthon function which takes a list and returns a list with the same elements, but the k'th element removed."
  [list1 L]
  (let [k (dec L)]
    (if (or (nil? list1) (neg? k))
      list1
      (concat (take k list1) (drop (inc k) list1)))))