(defn count_Occurrence
  "	Write a cljthon function to count the occurence of all elements of list in a vector."
  [tup lst]
  (let [freqs (frequencies (or tup []))]
    (vec (map #(get freqs % 0) (or lst [])))))