(defn remove_kth_element
  "Write a cljthon function which takes a list and returns a list with the same elements, but the k'th element removed."
  [list1 L]
  (let [k (dec L)]
    (cond
      (nil? list1) nil
      (or (nil? L) (neg? k)) list1
      :else
      (concat (take k list1) (drop (inc k) list1)))))