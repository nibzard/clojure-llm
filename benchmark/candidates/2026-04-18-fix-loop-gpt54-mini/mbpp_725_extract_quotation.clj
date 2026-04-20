(defn extract_quotation
  "	Write a function to extract values between quotation marks \" \" of the given string."
  [text1]
  (let [m (re-seq #"\"([^\"]*)\"" text1)]
    (map second m)))