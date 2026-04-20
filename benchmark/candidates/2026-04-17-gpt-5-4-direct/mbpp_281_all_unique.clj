(defn all_unique
  "	Write a cljthon function to check if the elements of a given list are unique or not."
  [test_list]
  (or (nil? test_list)
      (= (count test_list) (count (set test_list)))))