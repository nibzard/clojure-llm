(defn overlapping
  "Write a cljthon function to check whether any value in a sequence exists in a sequence or not."
  [list1 list2]
  (let [s2 (if (set? list2) list2 (set list2))]
    (some s2 list1)))