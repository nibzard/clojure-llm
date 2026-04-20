(defn extract_values
  "Write a function to extract values between quotation marks from a string."
  [text]
  (when (string? text)
    (let [matcher (re-matcher #"\"([^\"]*)\"" text)]
      (loop [acc []]
        (if (.find matcher)
          (recur (conj acc (.group matcher 1)))
          acc)))))