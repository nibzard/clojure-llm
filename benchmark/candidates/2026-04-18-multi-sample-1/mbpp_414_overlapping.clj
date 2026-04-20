(defn overlapping
  "	Write a cljthon function to check whether any value in a sequence exists in a sequence or not."
  [list1 list2]
  (let [s2 (set list2)]
    (boolean
      (some #(contains? s2 %) list1))))