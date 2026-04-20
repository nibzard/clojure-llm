(defn overlapping
  "	Write a cljthon function to check whether any value in a sequence exists in a sequence or not."
  [list1 list2]
  (boolean
   (when (and (seq list1) (seq list2))
     (some (set list2) list1))))