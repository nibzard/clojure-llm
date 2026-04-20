(defn remove_kth_element
  "Write a cljthon function which takes a list and returns a list with the same elements, but the k'th element removed."
  [list1 L]
  (concat (take L list1) (drop (inc L) list1)))