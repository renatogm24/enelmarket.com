import type { AppProps } from "next/app";
import type { LayoutProps } from "@vercel/edge-functions-ui/layout";
import { getLayout } from "@vercel/edge-functions-ui";
import "../index.css";
//import "@vercel/edge-functions-ui/globals.css";
import { ApolloProvider } from "@apollo/client";
import { useApollo } from "../lib/apollo";
import { AuthProvider } from "../context/AuthContext";

export default function MyApp({ Component, pageProps }: AppProps) {
  const Layout = getLayout<LayoutProps>(Component);
  const apolloClient = useApollo(pageProps.initialApolloState);

  return (
    <ApolloProvider client={apolloClient}>
      <AuthProvider>
        <Layout path="hostname-rewrites" deployButton={{ env: ["ROOT_URL"] }}>
          <Component {...pageProps} />
        </Layout>
      </AuthProvider>
    </ApolloProvider>
  );
}
