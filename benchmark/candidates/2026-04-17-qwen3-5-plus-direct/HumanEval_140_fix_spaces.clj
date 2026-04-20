(defn fix_spaces [text]
  (if (nil? text)
    ""
    (let [leading-count (count (or (re-find #"^ +" text) ""))
          trailing-count (count (or (re-find #" +$" text) ""))
          leading-replacement (case leading-count
                                0 ""
                                1 ""
                                2 "_"
                                "-")
          trailing-replacement (case trailing-count
                                 0 ""
                                 1 ""
                                 2 "_"
                                 "-")
          middle (-> text
                     (clojure.string/replace #"^ +" "")
                     (clojure.string/replace #" +$" "")
                     (clojure.string/replace #" {3,}" "-")
                     (clojure.string/replace #" {1,2}" "_"))]
      (str leading-replacement middle trailing-replacement))))